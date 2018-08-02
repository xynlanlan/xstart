package com.example.start.api.v100.controller;

import com.example.start.api.v100.dto.DecodeUserInfo;
import com.example.start.common.base.BaseController;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.utils.AES;
import com.example.start.common.utils.HttpClientUtils;
import com.example.start.module.service.SysUserService;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v100/user")
public class UserApiController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(UserApiController.class);

    @Value("${wx.jscode2session.url}")
    private String jscode2sessionUrl;

    @Value("${wx.pay.appId}")
    private String appId;

    @Value("${wx.pay.appSecret}")
    private String appSecret;

    @Autowired
    public SysUserService userService;
    @GetMapping("{id}")
    public Map<String, Object> findUserById(@PathVariable("id") Long id) throws ServiceException {
        return success(userService.findOne(id));
    }


    /**
     * 解密微信用户信息
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)
    public Map info(@RequestBody DecodeUserInfo info) {
        logger.info("=====decodeUserInfo参数:" +info.toString());
        Map<String,Object> map = new HashMap<>();
        if (info == null || info.getCode()== null) {
            map.put("status", 500);
            map.put("msg", "code 不能为空");
            return map;
        }
        String grant_type = "authorization_code";
        String params = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + info.getCode() + "&grant_type=" + grant_type;
        String sr = null;
        try {
            sr = HttpClientUtils.get(jscode2sessionUrl+params);
            JSONObject json = JSONObject.fromObject(sr);
            String session_key = json.get("session_key").toString();
            //String openid = (String) json.get("openid");

            byte[] result = AES.decrypt(Base64.decodeBase64(info.getEncryptedData()),Base64.decodeBase64(session_key)
                    ,Base64.decodeBase64(info.getIv()));
            if (null != result ) {
                String resultS = new String(result, "UTF-8");
                map.put("status", 200);
                map.put("msg", "登录成功!");

                JSONObject userInfoJSON = JSONObject.fromObject(resultS);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status",500);
        map.put("msg", "登录失败!");
        return map;
    }
}
