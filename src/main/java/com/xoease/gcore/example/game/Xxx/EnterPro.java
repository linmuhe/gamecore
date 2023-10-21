package com.xoease.gcore.example.game.Xxx;

import com.xoease.gcore.example.Action.ReqAc;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(ReqAc.REQ_ENTER)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EnterPro extends XxxBasePro {
    @Override
    public Object onEnter() {
        return new DSW();
    }


}
