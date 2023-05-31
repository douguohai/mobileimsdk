package net.x52im.mobileimsdk.service;

import lombok.extern.slf4j.Slf4j;
import net.x52im.mobileimsdk.event.ChatBaseEvent;

/**
 * <p>
 * java类作用描述
 * </p>
 *
 * @author : tianwen
 * @version : 1.0
 * @date : 2023/5/31 12:37
 **/
@Slf4j
public class ChatBaseEventImpl implements ChatBaseEvent {

    private final static String TAG = ChatBaseEventImpl.class.getSimpleName();


    /**
     * 本地用户的登陆结果回调事件通知。
     *
     * @param errorCode 服务端反馈的登录结果：0 表示登陆成功，否则为服务端自定义的出错代码（按照约定通常为>=1025的数）
     */
    @Override
    public void onLoginResponse(int errorCode) {
        if (errorCode == 0) {
            log.info( "【DEBUG_UI】IM服务器登录/重连成功！");

        } else {
            log.info(TAG, "【DEBUG_UI】IM服务器登录/连接失败，错误代码：" + errorCode);


        }


    }

    /**
     * 与服务端的通信断开的回调事件通知。
     * <br>
     * 该消息只有在客户端连接服务器成功之后网络异常中断之时触发。<br>
     * 导致与与服务端的通信断开的原因有（但不限于）：无线网络信号不稳定、网络切换等。
     *
     * @param errorCode 本回调参数表示表示连接断开的原因，目前错误码没有太多意义，仅作保留字段，目前通常为-1
     */
    @Override
    public void onLinkClose(int errorCode) {
        log.error(TAG, "【DEBUG_UI】与IM服务器的网络连接出错关闭了，error：" + errorCode);
    }

}
