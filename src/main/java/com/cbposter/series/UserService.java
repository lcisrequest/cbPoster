package com.cbposter.series;

import com.cbposter.dao.Skill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户类
 *
 * @Auther: lc
 * @Date: 2020/7/9 16:35
 */
@Service
public class UserService {

    public static List<Skill> skillList = null;

    {
        skillList = new ArrayList<>();

    }


}
