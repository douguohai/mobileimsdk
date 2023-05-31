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
 * ChatTransDataEvent.java at 2020-8-6 14:24:51, code by Jack Jiang.
 */
package net.x52im.mobileimsdk.event;

import net.x52im.mobileimsdk.server.protocal.ErrorCode;

public interface ChatMessageEvent {

    /**
     * 收到普通消息的回调事件通知。
     * <br>应用层可以将此消息进一步按自已的IM协议进行定义，从而实现完整的即时通信软件逻辑。
     *
     * @param fingerPrintOfProtocal 当该消息需要QoS支持时本回调参数为该消息的特征指纹码，否则为null
     * @param userid 消息的发送者id（MobileIMSDK框架中规定发送者id="0"即表示是由服务端主动发过的，否则
     * 表示的是其它客户端发过来的消息）
     * @param dataContent 消息内容的文本表示形式
     * @param typeu 意义：应用层专用字段——用于应用层存放聊天、推送等场景下的消息类型。 注意：此值为-1时表
     * 示未定义。MobileIMSDK框架中，本字段为保留字段，不参与框架的核心算法，专留用应用 层自行定义和使用。 默认：-1。
     * @see <a href="http://docs.52im.net/extend/docs/api/mobileimsdk/server_netty/net/openmob/mobileimsdk/server/protocal/Protocal.html" target="_blank">Protocal</a>
     */
    void onRecieveMessage(String fingerPrintOfProtocal, String userid, String dataContent, int typeu);

    /**
     * 服务端反馈的出错信息回调事件通知。
     *
     * @param errorCode 错误码，定义在常量表{@link ErrorCode.ForS}中
     * @param errorMsg 描述错误内容的文本信息
     */
    void onErrorResponse(int errorCode, String errorMsg);
}