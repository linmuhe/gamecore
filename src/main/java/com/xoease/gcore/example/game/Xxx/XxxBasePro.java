package com.xoease.gcore.example.game.Xxx;

import com.xoease.gcore.example.game.BasePro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XxxBasePro extends BasePro {
    protected static final Logger log = LoggerFactory.getLogger(XxxBasePro.class);
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.err.println("close .......");
    }
}
