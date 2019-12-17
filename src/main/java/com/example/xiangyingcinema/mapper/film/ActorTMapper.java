package com.example.xiangyingcinema.mapper.film;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.xiangyingcinema.model.film.ActorT;
import com.example.xiangyingcinema.vo.film.ActorVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 */
public interface ActorTMapper extends BaseMapper<ActorT> {

    List<ActorVO> getActors(@Param("filmId") String filmId);

}