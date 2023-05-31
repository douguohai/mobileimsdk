package net.x52im.mobileimsdk.service;


import net.x52im.mobileimsdk.ClientCoreSDK;
import net.x52im.mobileimsdk.conf.ConfigEntity;
import net.x52im.mobileimsdk.core.LocalSocketProvider;

/**
 * MobileIMSDK的管理类。
 *
 * @author Jack Jiang(http://www.52im.net/thread-2792-1-1.html)
 */
public class IMClientManager {
    private static String TAG = IMClientManager.class.getSimpleName();

    private static IMClientManager instance = null;

    /**
     * MobileIMSDK是否已被初始化. true表示已初化完成，否则未初始化.
     */
    private boolean init = false;

    /**
     * 基本连接状态事件监听器
     */
    private ChatBaseEventImpl baseEventListener = null;
    /**
     * 数据接收事件监听器
     */
    private ChatMessageEventImpl transDataListener = null;
    /**
     * 消息送达保证事件监听器
     */
    private MessageQoSEventImpl messageQoSListener = null;

    public static IMClientManager getInstance() {
        if (instance == null) {
            instance = new IMClientManager();
        }
        return instance;
    }

    private IMClientManager() {
        initMobileIMSDK();
    }

    /**
     * MobileIMSDK的初始化方法。
     */
    public void initMobileIMSDK() {
        if (!init) {

            // 设置服务器ip和服务器端口
			ConfigEntity.serverIP = "127.0.0.1";
//			ConfigEntity.serverIP = "rbcore.openmob.net";
			ConfigEntity.serverPort = 7901;

            // MobileIMSDK核心IM框架的敏感度模式设置
            ConfigEntity.setSenseMode(ConfigEntity.SenseMode.MODE_10S);

            // 设置最大TCP帧内容长度（不设置则默认最大是 6 * 1024字节）
			LocalSocketProvider.TCP_FRAME_MAX_BODY_LENGTH = 60 * 1024;

            // 开启/关闭DEBUG信息输出
            ClientCoreSDK.DEBUG = true;

            // 开启SSL/TLS加密传输（请确保服务端也已开启SSL）
//	    	LocalSocketProvider.sslContext = createSslContext();

            // 设置事件回调
            baseEventListener = new ChatBaseEventImpl();
            transDataListener = new ChatMessageEventImpl();
            messageQoSListener = new MessageQoSEventImpl();
            ClientCoreSDK.getInstance().setChatBaseEvent(baseEventListener);
            ClientCoreSDK.getInstance().setChatMessageEvent(transDataListener);
            ClientCoreSDK.getInstance().setMessageQoSEvent(messageQoSListener);

            init = true;
        }
    }



    /**
     * MobileIMSDK的资源释放方法（退出SDK时使用）。
     */
    public void release() {
        ClientCoreSDK.getInstance().release();
        resetInitFlag();
    }

    /**
     * 重置init标识。
     * <p>
     * <b>重要说明：</b>不退出APP的情况下，重新登陆时记得调用一下本方法，不然再
     * 次调用 {@link #initMobileIMSDK()} 时也不会重新初始化MobileIMSDK（
     * 详见 {@link #initMobileIMSDK()}代码）而报 code=203错误！
     */
    public void resetInitFlag() {
        init = false;
    }

    public ChatMessageEventImpl getChatMessageListener() {
        return transDataListener;
    }

    public ChatBaseEventImpl getBaseEventListener() {
        return baseEventListener;
    }

    public MessageQoSEventImpl getMessageQoSListener() {
        return messageQoSListener;
    }

    public static void main(String[] args) {
        IMClientManager imClientManager= IMClientManager.getInstance();
    }
}

