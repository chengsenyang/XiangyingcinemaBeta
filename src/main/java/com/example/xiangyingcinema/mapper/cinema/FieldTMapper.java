package com.example.xiangyingcinema.mapper.cinema;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.xiangyingcinema.model.cinema.FieldT;
import com.example.xiangyingcinema.vo.cinema.FilmInfoVO;
import com.example.xiangyingcinema.vo.cinema.HallInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 */
public interface FieldTMapper extends BaseMapper<FieldT> {

    List<FilmInfoVO> getFilmInfos(@Param("cinemaId") int cinemaId);

    HallInfoVO getHallInfo(@Param("fieldId") int fieldId);

    FilmInfoVO getFilmInfoById(@Param("fieldId") int fieldId);

}
