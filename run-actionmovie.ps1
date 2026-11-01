$ErrorActionPreference = 'Stop'

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$project = Join-Path $root 'XemPhim'
$tomcat = Join-Path $root 'tools\apache-tomcat-9.0.99'
$deploy = Join-Path $tomcat 'webapps\XemPhim'
$javaHome = 'C:\Program Files\Java\jdk-21.0.12'

if (-not (Test-Path (Join-Path $javaHome 'bin\javac.exe'))) {
    throw "JDK not found at $javaHome"
}
if (-not (Test-Path (Join-Path $tomcat 'bin\catalina.bat'))) {
    throw "Tomcat not found at $tomcat"
}

$env:JAVA_HOME = $javaHome
$env:JRE_HOME = $javaHome
$env:CATALINA_HOME = $tomcat
$env:CATALINA_BASE = $tomcat

if (Test-Path $deploy) {
    Remove-Item $deploy -Recurse -Force
}
New-Item -ItemType Directory -Path (Join-Path $deploy 'WEB-INF\classes') -Force | Out-Null
Copy-Item (Join-Path $project 'src\main\webapp\*') $deploy -Recurse -Force

$classpath = "$(Join-Path $tomcat 'lib\*');$(Join-Path $project 'src\main\webapp\WEB-INF\lib\*')"
$sources = Get-ChildItem (Join-Path $project 'src\main\java') -Recurse -Filter '*.java' |
    Select-Object -ExpandProperty FullName
& (Join-Path $javaHome 'bin\javac.exe') --release 17 -encoding UTF-8 -cp $classpath `
    -d (Join-Path $deploy 'WEB-INF\classes') $sources
if ($LASTEXITCODE -ne 0) {
    throw "Java compilation failed."
}

Write-Host 'Build and deploy completed.'
& (Join-Path $tomcat 'bin\catalina.bat') run
