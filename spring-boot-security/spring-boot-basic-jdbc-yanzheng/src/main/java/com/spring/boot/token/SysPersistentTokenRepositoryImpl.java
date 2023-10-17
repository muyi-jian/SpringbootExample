package com.spring.boot.token;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.spring.boot.entity.SysToken;
import com.spring.boot.mapper.SysTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysPersistentTokenRepositoryImpl implements PersistentTokenRepository {

    @Autowired
    SysTokenMapper sysTokenMapper;
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        SysToken sysToken = new SysToken();
        sysToken.setUsername(token.getUsername());
        sysToken.setSeries(token.getSeries());
        sysToken.setTokenValue(token.getTokenValue());
        sysToken.setDate(token.getDate());
        sysTokenMapper.insert(sysToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("token",tokenValue);
        updateWrapper.set("date",lastUsed);
        updateWrapper.eq("series",series);

        sysTokenMapper.update(null,updateWrapper);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        SysToken sysToken = sysTokenMapper.selectOne(new QueryWrapper<SysToken>().eq("series", seriesId));;
        if (sysToken == null){
            return null;
        }
        PersistentRememberMeToken persistentRememberMeToken = new PersistentRememberMeToken(sysToken.getUsername(), sysToken.getSeries(), sysToken.getTokenValue(), sysToken.getDate());

        return persistentRememberMeToken;
    }

    @Override
    public void removeUserTokens(String username) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("username",username);
        sysTokenMapper.delete(updateWrapper);
    }
}
