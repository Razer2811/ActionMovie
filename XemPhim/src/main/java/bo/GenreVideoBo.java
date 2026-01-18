package bo;

import java.util.ArrayList;

import dao.GenreVideoDao;
import modal.GenreVideo;

public class GenreVideoBo {

    GenreVideoDao gdao = new GenreVideoDao();

    public ArrayList<GenreVideo> getGenreVideo() throws Exception {
        return gdao.getGenreVideo();
    }

    public GenreVideo timTheoID(Integer id) throws Exception {
        return gdao.timtheoID(id);
    }

    public int themLoai(String maLoai, String tenLoai) throws Exception {
        return gdao.themLoai(maLoai, tenLoai);
    }

    public int suaLoai(String maLoai, String tenLoai) throws Exception {
        return gdao.suaLoai(maLoai, tenLoai);
    }

    public int xoaLoai(String maLoai) throws Exception {
        return gdao.xoaLoai(maLoai);
    }
}
