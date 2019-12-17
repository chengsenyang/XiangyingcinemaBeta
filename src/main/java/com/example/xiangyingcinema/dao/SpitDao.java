package com.example.xiangyingcinema.dao;

import com.example.xiangyingcinema.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SpitDao extends JpaRepository<Spit,String> , JpaSpecificationExecutor<Spit> {
    //, JpaSpecificationExecutor<Spit>

    public Page<Spit> findByParentid(String parentid, Pageable pageable);

    public List<Spit> findByParentidIsNull();

    public void deleteByNickname(String nickname);

    public List<Spit> findByNickname(String nickname);

}
