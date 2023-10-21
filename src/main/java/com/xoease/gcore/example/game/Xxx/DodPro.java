package com.xoease.gcore.example.game.Xxx;

import com.xoease.gcore.Pack;
import com.xoease.gcore.example.Action.Reposon;
import com.xoease.gcore.example.Action.ReqAc;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(ReqAc.REQ_H)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DodPro extends XxxBasePro {
    @Override
    public void handler(Object o, Reposon res) {
        res.send(new Pack(ReqAc.RES_UINFO,"xxx"));
    }


}
