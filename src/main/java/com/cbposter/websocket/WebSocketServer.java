package com.cbposter.websocket;

import com.cbposter.series.AdventureService;
import com.cbposter.series.PractiseService;
import com.cbposter.series.RoleService;
import com.cbposter.series.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import javax.management.relation.RoleResult;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/websocket/{pageCode}")
@Component
public class WebSocketServer {

    @Autowired
    UserService userService;
    @Autowired
    AdventureService adventureService;
    @Autowired
    PractiseService practiseService;
    @Autowired
    RoleService roleService;

    private static final String loggerName = WebSocketServer.class.getName();
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    public static Map<String, List<Session>> electricSocketMap = new ConcurrentHashMap<String, List<Session>>();
    public static List<String> sessionIdList = new ArrayList<>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("pageCode") String pageCode, Session session) throws IOException {
        List<Session> sessions = electricSocketMap.get(pageCode);
        if (null == sessions) {
            List<Session> sessionList = new ArrayList<>();
            sessionList.add(session);
            electricSocketMap.put(pageCode, sessionList);
        } else {
            sessions.add(session);
        }

        session.getBasicRemote().sendText("欢迎链接lc的武侠世界\r\n");
        session.getBasicRemote().sendText("请输入正确指令进行操作\r\n");
        session.getBasicRemote().sendText("1:创建新角色  2:登录已有角色(暂不支持)\r\n");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("pageCode") String pageCode, Session session) {
        if (electricSocketMap.containsKey(pageCode)) {
            electricSocketMap.get(pageCode).remove(session);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("client message=" + message);
        if ("1".equals(message)) {
            if (!sessionIdList.contains(session.getId())) {
                session.getBasicRemote().sendText("创建成功，正在随机生成角色数据");
                sessionIdList.add(session.getId());
            }
        } else if ("2".equals(message)) {
            session.getBasicRemote().sendText("暂不支持角色登录");
        } else {
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        ;
    }
}