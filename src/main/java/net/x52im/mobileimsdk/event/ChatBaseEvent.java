/*
 * Copyright (C) 2020  即时通讯网(52im.net) & Jack Jiang.
 * The MobileIMSDK_TCP (MobileIMSDK v5.x TCP版) Project.
 * All rights reserved.
 *
 * > Github地址：https://github.com/JackJiang2011/MobileIMSDK
 * > 文档地址：  http://www.52im.net/forum-89-1.html
 * > 技术社区：  http://www.52im.net/
 * > 技术交流群：320837163 (http://www.52im.net/topic-qqgroup.html)
 * > 作者公众号：“即时通讯技术圈】”，欢迎关注！
 * > 联系作者：  http://www.52im.net/thread-2792-1-1.html
 *
 * "即时通讯网(52im.net) - 即时通讯开发者社区!" 推荐开源工程。
 *
 * ChatBaseEvent.java at 2020-8-6 14:24:51, code by Jack Jiang.
 */
package net.x52im.mobileimsdk.event;

/**
 * @author tianwen
 */
public interface ChatBaseEvent {

    /**
     * 本地用户的登陆结果回调事件通知。
     *
     * @param errorCode 服务端反馈的登录结果：0 表示登陆成功，否则为服务端自定义的出错代码（按照约定通常为>=1025的数）
     */
    public void onLoginResponse(int errorCode);

    /**
     * 与服务端的通信断开的回调事件通知。
     * <br>
     * 该消息只有在客户端连接服务器成功之后网络异常中断之时触发。<br>
     * 导致与与服务端的通信断开的原因有（但不限于）：无线网络信号不稳定、网络切换等。
     *
     * @param errorCode 本回调参数表示表示连接断开的原因，目前错误码没有太多意义，仅作保留字段，目前通常为-1
     */
    public void onLinkClose(int errorCode);
}
