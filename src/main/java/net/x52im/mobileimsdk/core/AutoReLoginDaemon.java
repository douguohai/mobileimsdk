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
 * AutoReLoginDaemon.java at 2020-8-6 14:24:51, code by Jack Jiang.
 */
package net.x52im.mobileimsdk.core;

import net.x52im.mobileimsdk.ClientCoreSDK;
import net.x52im.mobileimsdk.utils.Log;

import javax.swing.*;


/**
 * 系统自动重新登录服务
 * @author tianwen
 */
public class AutoReLoginDaemon {

    /**
     * 模块简化名称
     */
    private final static String TAG = AutoReLoginDaemon.class.getSimpleName();

    /**
     * 单例实例实例
     */
    private static AutoReLoginDaemon instance = null;

    /**
     * 定时重新登录时间
     */
    public static int AUTO_RE$LOGIN_INTERVAL = 3000;

    /**
     * 当前进程是否是否在运行
     */
    private boolean autoReLoginRunning = false;

    /**
     * 是否正在执行
     */
    private boolean execute = false;

    /**
     * 定时器
     */
    private Timer timer = null;

    /**
     * 获取实例信息
     * @return 实例对象
     */
    public static AutoReLoginDaemon getInstance() {
        if (instance == null) {
            instance = new AutoReLoginDaemon();
        }
        return instance;
    }

    private AutoReLoginDaemon() {
        init();
    }

    private void init() {
        timer = new Timer(AUTO_RE$LOGIN_INTERVAL, e -> run());
    }

    public void run() {
        if (!execute) {
            execute = true;
            if (ClientCoreSDK.DEBUG) {
                Log.p(TAG, "【IMCORE】自动重新登陆线程执行中, autoReLogin?" + ClientCoreSDK.autoReLogin + "...");
            }
            int code = -1;
            if (ClientCoreSDK.autoReLogin) {
                LocalSocketProvider.getInstance().closeLocalSocket();
                code = LocalDataSender.getInstance().sendLogin(
                        ClientCoreSDK.getInstance().getCurrentLoginUserId(),
                        ClientCoreSDK.getInstance().getCurrentLoginToken(),
                        ClientCoreSDK.getInstance().getCurrentLoginExtra());
            }

            if (code == 0) {
                // LocalUDPDataReciever.getInstance().startup();
            }

            //
            execute = false;
        }
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
        }

        autoReLoginRunning = false;
    }

    public void start(boolean immediately) {
        stop();
        if (immediately) {
            timer.setInitialDelay(0);
        } else {
            timer.setInitialDelay(AUTO_RE$LOGIN_INTERVAL);
        }
        timer.start();
        autoReLoginRunning = true;
    }

    public boolean isautoReLoginRunning() {
        return autoReLoginRunning;
    }
}
