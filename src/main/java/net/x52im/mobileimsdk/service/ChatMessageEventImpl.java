package net.x52im.mobileimsdk.service;

import lombok.extern.slf4j.Slf4j;
import net.x52im.mobileimsdk.event.ChatMessageEvent;
import net.x52im.mobileimsdk.server.protocal.ErrorCode;

/**
 * 与IM服务器的数据交互事件在此ChatTransDataEvent子类中实现即可。
 *
 * @author Jack Jiang, 20170501
 * @version.1.1
 */
@Slf4j
public class ChatMessageEventImpl implements ChatMessageEvent {
    private final static String TAG = ChatMessageEventImpl.class.getSimpleName();


    /**
     * 收到普通消息的回调事件通知。
     * <br>应用层可以将此消息进一步按自已的IM协议进行定义，从而实现完整的即时通信软件逻辑。
     *
     * @param fingerPrintOfProtocal 当该消息需要QoS支持时本回调参数为该消息的特征指纹码，否则为null
     * @param userid                消息的发送者id（MobileIMSDK框架中规定发送者id="0"即表示是由服务端主动发过的，否则
     *                              表示的是其它客户端发过来的消息）
     * @param dataContent           消息内容的文本表示形式
     * @param typeu                 意义：应用层专用字段——用于应用层存放聊天、推送等场景下的消息类型。 注意：此值为-1时表
     *                              示未定义。MobileIMSDK框架中，本字段为保留字段，不参与框架的核心算法，专留用应用 层自行定义和使用。 默认：-1。
     * @see <a href="http://docs.52im.net/extend/docs/api/mobileimsdk/server_netty/net/openmob/mobileimsdk/server/protocal/Protocal.html" target="_blank">Protocal</a>
     */
    @Override
    public void onRecieveMessage(String fingerPrintOfProtocal, String userid, String dataContent, int typeu) {
        log.info(TAG, "【DEBUG_UI】[typeu=" + typeu + "]收到来自用户" + userid + "的消息:" + dataContent);


    }

    /**
     * 服务端反馈的出错信息回调事件通知。
     *
     * @param errorCode 错误码，定义在常量表{@link ErrorCode.ForS}中
     * @param errorMsg  描述错误内容的文本信息
     */
    @Override
    public void onErrorResponse(int errorCode, String errorMsg) {
        log.info(TAG, "【DEBUG_UI】收到服务端错误消息，errorCode=" + errorCode + ", errorMsg=" + errorMsg);
    }

}

