package com.cbposter.series;

import com.cbposter.dao.SkillRepository;
import com.cbposter.dao.UserRepository;
import com.cbposter.model.Goods;
import com.cbposter.model.Role;
import com.cbposter.model.Skill;
import com.cbposter.model.User;
import com.cbposter.utils.MathUtil;
import com.cbposter.utils.StringUtil;
import com.cbposter.utils.exception.RException;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * 用户类
 *
 * @Auther: lc
 * @Date: 2020/7/9 16:35
 */
@Service
public class UserService {
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    UserRepository userRepository;

    public static Map<Session, Role> userMap = new HashMap<>();

    //获取角色初始技能
    public Skill getCharacterInitialSkills() {
        List<Skill> skillList = skillRepository.findDataLikeDescription(3, 0);
        if (skillList.size() == 0) {
            throw new RException("获取初始化技能出错，数据库没有相关数据，请联系管理员");
        }
        int index = MathUtil.getRandom(0, skillList.size() - 1);
        Skill skill = skillList.get(index);
        return skill;
    }


    public Role getInitializeRole() {
        List<Goods> goodsList = new ArrayList<>();
        List<Skill> skillList = new ArrayList<>();
        Role role = new Role();
        int sex = MathUtil.getRandom(0, 1);
        String name = StringUtil.getChineseName();
        String identity = getRandomIdentity();
        String looks = getRandomLooks(sex);
        role.setSex(sex);
        role.setName(name);
        role.setIdentity(identity);
        role.setLooks(looks);
        role.setMoney(500);
        role.setAttack(10);
        role.setBlood(100);
        role.setDefense(5);
        role.setGoods(goodsList);
        role.setSkills(skillList);
        return role;
    }


    /**
     * 获取随机样貌
     */
    public String getRandomLooks(int sex) {
        if (sex == 1) {
            ImmutableList<String> iList = ImmutableList.of("鸱目虎吻", "鹤发鸡皮", "尖嘴猴腮", "獐头鼠目", "美如冠玉", "其貌不扬", "平平无奇", "文质彬彬", "英俊潇洒", "高大威猛", "玉树临风", "三分似人，七分似鬼");
            return iList.get(MathUtil.getRandom(0, iList.size() - 1));
        } else {
            ImmutableList<String> iList = ImmutableList.of("闭月羞花", "出水芙蓉", "倾国倾城", "眉清目秀", "如花似玉", "玉树临风", "鹰头雀脑", "尖嘴猴腮", "獐头鼠目", "可可爱爱", "平平无奇", "三分似人，七分似鬼");
            return iList.get(MathUtil.getRandom(0, iList.size() - 1));
        }

    }

    /**
     * 获取随机身份
     */
    public String getRandomIdentity() {
        ImmutableList<String> iList = ImmutableList.of("江湖散人", "流浪乞丐");

        return iList.get(MathUtil.getRandom(0, iList.size() - 1));
    }

    public String createUser(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            return "注册失败，该用户名已存在";
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userRepository.saveAndFlush(user);
        return "注册成功，请重新登录";
    }

    public String loginUser(String username, String password, Session session) throws IOException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            session.getBasicRemote().sendText("登录失败，该用户名不存在");
        }
        String thepassword = user.getPassword();
        if (thepassword.equals(password)) {
            Role role = user.getRole();
            if (role == null) {
                role = getInitializeRole();
                user.setRole(role);
                //todo role入库
            }
            userMap.put(session, user.getRole());
            return "登录成功";
        } else {
            return "登录失败，密码错误";
        }

    }


}
