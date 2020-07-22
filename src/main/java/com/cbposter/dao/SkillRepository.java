package com.cbposter.dao;


import com.cbposter.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: lc
 * @Date: 2020/7/22 11:50
 */
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query(value = "select * from skill where rarity > ?1 and rarity < ?2 ", nativeQuery = true)
    List<Skill> findDataLikeDescription(int bigrarity, int smallrarity);

}
