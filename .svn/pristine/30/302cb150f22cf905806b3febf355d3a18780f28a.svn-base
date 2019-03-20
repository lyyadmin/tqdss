package com.tenly.project.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.tenly.common.projecttools.PlatFormPar;
import com.tenly.common.projecttools.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tenly.common.projecttools.Commons;
import com.tenly.common.projecttools.Constant;
import com.tenly.project.bean.DataLose;
import com.tenly.project.bean.YwDriverOperationEvaluation;
import com.tenly.project.service.ICheckOutService;
import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysUser;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("all")
@Controller
@RequestMapping("/checkout/checkoutAnalysis")
public class CheckOutController {
    @Autowired
    private ICheckOutService checkOutService;
    Logger logger = Logger.getLogger(this.getClass());

    @SuppressWarnings("rawtypes")
    @RequestMapping("/checkout")
    public String checkOut(HttpServletRequest request, HttpServletResponse response, String driver_code) {
        /**
         * 把司机的基本信息返回去  计算放在二次请求中去
         */
        request.setAttribute("driver_code", driver_code);
        return "/project/lzm/checkout-analysis/checkout-1";
    }

    /**
     * 处理是否正在出勤中,这里需要调用运安接口，判断逻辑：如果运安中查询没有退勤时间，那么这个人就还没有退勤，正在出勤中
     * @param request
     * @param response
     * @param driver_code
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/queryCheckining")
    public @ResponseBody
    String queryCheckining(HttpServletRequest request, HttpServletResponse response, String driver_code) {
        String bl = "";
        try {
            bl = checkOutService.queryCheckining(driver_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 发送短信
     * @param driverCode
     * @param sendContent
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("sendMessages")
    public @ResponseBody
    String sendMessages(String driverCode, String sendContent) {
        String returnContent = "";
        try {
            returnContent = checkOutService.sendMessages(driverCode, sendContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnContent;
    }

    /**
     * 查询乘务员的基本信息
     *
     * @param request
     * @param response
     * @param driver_id
     * @return
     */
    @RequestMapping("/queryCurrentCheckOut")
    public @ResponseBody
    Map<String, Object> queryCurrentCheckOut(HttpServletRequest request,
                                             HttpServletResponse response, String driver_id) {
        Map<String, Object> queryCurrentCheckOut = checkOutService.queryCurrentCheckOut(driver_id);
        return queryCurrentCheckOut;
    }

    /**
     * 乘务员退勤分析的详情分析结果
     * @param request
     * @param response
     * @param driver_id
     * @return
     */
    @RequestMapping("/queryCurrentDriverAnalyData")
    public ModelAndView queryCurrentDriverAnalyData(HttpServletRequest request, HttpServletResponse response
            , String driver_id, String page, String pageSize) {
        PageBean pageBean = new PageBean();
        List<Map<String, Object>> rows = null;
        try {
            if (StringUtils.isNotBlank(driver_id) && !StringUtils.isEmpty(driver_id)) {
                if (Constant.BL) {//测试跟正式用的
                    checkOutService.testQueryCurrentDriverAnalyDataGrid(driver_id.trim(), pageBean);
                } else {
                    checkOutService.queryCurrentDriverAnalyData(driver_id.trim(), pageBean);
                }
            }
            rows = pageBean.getRows();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONArray.toJSONString(rows).toString());
        } catch (Exception e) {
            logger.error("退勤页面查询项点出错");
            logger.error(e);
            return new ModelAndView("/error");
        }
        return null;
    }

    /**
     * 项点数据
     * @param request
     * @param response
     * @param driver_id
     * @param problem_level
     * @return
     */
    @RequestMapping("/getItemGridData")
    public @ResponseBody
    PageBean getItemGridData(HttpServletRequest request, HttpServletResponse response
            , String driver_id, String problem_level) {
        PageBean pageBean = new PageBean();
        try {
            if (StringUtils.isNotBlank(driver_id) && !StringUtils.isEmpty(driver_id)) {
                if (Constant.BL) {//测试跟正式用的
                    checkOutService.testQueryCurrentDriverAnalyDataGrid(driver_id.trim(), pageBean);
                } else {
                    checkOutService.queryCurrentDriverAnalyData(driver_id.trim(), pageBean);
                }
                List<Map<String, Object>> rows = pageBean.getRows();
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                if ("1".equals(problem_level)) {
                    for (Map<String, Object> oo : rows) {
                        String level = (String) oo.get("LEVEL");
                        if ("A".equalsIgnoreCase(level) || "B".equalsIgnoreCase(level)) {
                            list.add(oo);
                        }
                    }
                    pageBean.setRows(list);
                } else if ("3".equals(problem_level)) {
                    for (Map<String, Object> oo : rows) {
                        String level = (String) oo.get("LEVEL");
                        if ("C".equalsIgnoreCase(level)) {
                            list.add(oo);
                        }
                    }
                    pageBean.setRows(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageBean;
    }


    /**
     * 查询退勤结果数据，编制退勤结果信息
     *
     * @param driver_code
     * @return
     */
    @RequestMapping("/getCheckOutResultData")
    public @ResponseBody
    List<Map<String, Object>> getCheckOutResultData(String driver_code) {
        PageBean pageBean = new PageBean();
        if (StringUtils.isNotBlank(driver_code) && !StringUtils.isEmpty(driver_code)) {
            try {
                if (Constant.BL) {//测试跟正式用的
                    checkOutService.testQueryCurrentDriverAnalyDataGrid(driver_code.trim(), pageBean);
                } else {
                    checkOutService.queryCurrentDriverAnalyData(driver_code.trim(), pageBean);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (List<Map<String, Object>>) pageBean.getRows();
    }

    /**
     * 保存存在有异议的问题信息
     *
     * @param request
     * @param response
     * @RequestBody YwDriverOperationEvaluation[] rowObj   获取数据方式对象数据，前端ajax直接传入json数组
     * dataLose    				数据丢失原因
     * checkInAndOutTimes 		出退勤时间
     * sasData     				始发终到完整数据
     * driver_id   				乘务员编号
     */
    @ResponseBody
    @RequestMapping("/saveAll")
    public String saveAll(HttpServletRequest request,
                          HttpServletResponse response,
                          String dataLose,
                          String checkInAndOutTimes,
                          String sasData,
                          String driver_id,
                          String checkoutResult) {
        PageBean pageBean = new PageBean();
        String isSuccess = "";
        try {
            //数据丢失确认的所有数据
            List<DataLose> dataLoseList = Commons.jsonToList(dataLose, DataLose.class);
            //始发终到的完整数据
            Object o = JSON.parse(sasData);
            List<Map<String, Object>> ol = (List<Map<String, Object>>) o;
            //获取数据分析结果表格中的所有数据，直接调用后台接口获取
            if (Constant.BL) {//测试跟正式用的
                checkOutService.testQueryCurrentDriverAnalyDataGrid(driver_id.trim(), pageBean);
            } else {
                checkOutService.queryCurrentDriverAnalyData(driver_id.trim(), pageBean);
            }

            checkOutService.saveAll(dataLoseList, ol,
                    driver_id.trim(), checkInAndOutTimes,
                    (List<Map<String, Object>>) pageBean.getRows(), checkoutResult);
            isSuccess = "1";
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = "0";
        }
        return isSuccess;
    }

    /**
     * 项点问题
     *
     * @param checkOut
     * @return
     */
    @ResponseBody
    @RequestMapping("/itemsDetailPro")
    public PageBean itemsDetailPro(YwDriverOperationEvaluation checkOut, String page, String pageSize) {
        PageBean pageBean = new PageBean();
        List<Map<String, Object>> list = null;
        try {
            list = checkOutService.itemsDetailPro(checkOut);
            pageBean.setRows(list);
            Commons.getBuildPageBeanList(page, pageSize,
                    pageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageBean;
    }

    /**
     * |查询当前司机的始发终到
     *
     * @return
     */
    @RequestMapping("/currentDriverStartAndEnd")
    public ModelAndView currentDriverStartAndEnd(HttpServletRequest request,
                                                 HttpServletResponse response,String driverId) {
        try {
            Map<String, Object> currentDriverStartAndEnd = null;
            if (Constant.BL) {
                //currentDriverStartAndEnd = checkOutService.testCurrentDriverStartAndEnd(driverId);
            } else {
                //currentDriverStartAndEnd = checkOutService.currentDriverStartAndEnd(driverId);
                currentDriverStartAndEnd = checkOutService.getDriverJLWithStartAndEndStation(driverId);
            }
            String s = JSONArray.toJSONString(currentDriverStartAndEnd).toString();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(s);
        } catch (Exception e) {
            logger.error("始发终到数据查询出错");
            e.printStackTrace();
            return new ModelAndView("/error");
        }
        return null;
    }

    /**
     * 查询文件缺失原因
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLoseCause")
    public List<Map<String, Object>> getLoseCause() {
        List<Map<String, Object>> list = checkOutService.getLoseCause();
        return list;
    }

    /**
     * 获取司机对分析结果的一个异常原因
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getExceptionCause")
    public List<Map<String, Object>> getExceptionCause() {
        List<Map<String, Object>> exceptionCause = null;
        try {
            exceptionCause = checkOutService.getExceptionCause();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exceptionCause;
    }

    /**
     * 获取作业项目问题措施
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAllItemsProblem")
    public List<Map<String, Object>> findAllItemsProblem() {
        List<Map<String, Object>> exceptionCause = null;
        try {
            exceptionCause = checkOutService.findAllItemsProblem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exceptionCause;
    }

    /**
     * 获取作业项目问题措施，以及作业达到的扣分级别
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCheckOutResultDetailAndLevel")
    public List<Map<String, Object>> getCheckOutResultDetailAndLevel() {
        List<Map<String, Object>> exceptionCause = null;
        try {
            exceptionCause = checkOutService.getCheckOutResultDetailAndLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exceptionCause;
    }

    /**
     * 通过司机编号二次查询
     *
     * @return String
     * @ResponseBody
     * @RequestMapping("/getCheckOutResultDetailAndLevel")
     */
    @ResponseBody
    @RequestMapping("/queryDriverSecondRequest")
    public String queryDriverSecondRequest(String driver_id) {
        String isExists = "";
        try {
            isExists = checkOutService.queryDriverSecondRequest(driver_id);
        } catch (Exception e) {
            isExists = "0";
            logger.error(e);
        }
        return isExists;
    }

    /**
     * 保存文件丢失原因   （包含  数据丢失   lkj和运安对接一方没有、数据在一段时间范围内丢失
     *
     * @param drivertime      开车时间
     * @param shifashijian    始发时间
     * @param shifazhan       始发站
     * @param sjh             乘务员编号
     * @param traincheci      车次
     * @param zhongdaoshijian 终到站
     * @param zhongdaozhan    终到时间
     * @param file_lose_cause 文件缺失原因
     * @return String
     */
    @ResponseBody
    @RequestMapping("/saveDataLoseCause")
    public String saveDataLoseCause(String drivertime, String shifashijian, String shifazhan,
                                    String sjh, String traincheci, String zhongdaoshijian, String zhongdaozhan,
                                    String file_lose_cause) {
        String res = "";
        try {
            checkOutService.saveDataLoseCause(sjh, traincheci, file_lose_cause);
            res = "1";
        } catch (Exception e) {
            res = "0";
            logger.error(e);
            //e.printStackTrace();
        }
        return res;
    }


    /**
     * 查询当前登录的地理位置
     * 得到当前登录位置的机务段的乘务员编号首两位
     *
     * @return
     */
    //queryPositionToDriverId
    @ResponseBody
    @RequestMapping("/queryPositionToDriverId")
    public List<Map<String, Object>> queryPositionToDriverId(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> list = null;
        try {
            list = checkOutService.findAllPositionToDriverId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过机务段段代码获取机务段对机车乘务员编写工号的规则
     * 并获取其前两位数
     *
     * @param request
     * @param response
     * @param jiwuduan_code 机务段段代码
     * @return List<Map               <               String               ,               Object>>
     */
    @ResponseBody
    @RequestMapping("/findCodeRuleByJiWuDuanCode")
    public List<Map<String, Object>> findCodeRuleByJiWuDuanCode(
            HttpServletRequest request, HttpServletResponse response
            , String jiwuduan_code) {
        List<Map<String, Object>> list = null;
        try {
            list = checkOutService.findCodeRuleByJiWuDuanCode(jiwuduan_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("/queryDriverIsExsits")
    public String queryDriverIsExsits(HttpServletRequest request, HttpServletResponse response
            , String driver_code) {
        Map<String, Object> map = null;
        String res = "";
        try {
            map = checkOutService.queryDriverIsExsits("", driver_code);
            if (map == null || map.size() < 1) {
                res = "0";
            } else {
                res = "1";
            }
        } catch (Exception e) {
            res = "0";
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 检测用户是否登录过
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryLoginStatus")
    public String queryLoginStatus(HttpServletRequest request, HttpServletResponse response) {
        String res = "";
        try {
            SysUser sysUser = (SysUser) request.getSession().getAttribute("sissionuser");
            if (sysUser == null && "".equals(sysUser)) {
                res = "0";
            } else {
                res = "1";//已经登录过
            }
        } catch (Exception e) {
            res = "0";
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 项点分析结果  数据存在异议的数据
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveExceptionDatas")
    public String saveExceptionData(String object) {
        List<YwDriverOperationEvaluation> ywDriverOperationEvaluations =
                Commons.jsonToList(object, YwDriverOperationEvaluation.class);
        String res = "";
        try {
            checkOutService.saveExceptionData(ywDriverOperationEvaluations);
            res = "1";
        } catch (Exception e) {
            res = "0";
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 查询lkj历史记录
     * 说明：查询的是分析出来的问题的原因
     *
     * @param page
     * @param pageSize
     * @param time           时间
     * @param train_batch_no 车次
     * @param driver_id      乘务员编号
     * @param region         记录行数
     * @return
     */
    @ResponseBody
    @RequestMapping("/findHostoryRecordWithLKJ")
    public PageBean findHostoryRecordWithLKJ(String page, String pageSize
            , String date, String time, String train_batch_no, String driver_id, String region) {
        PageBean pageBean = new PageBean();
        try {

            List<LinkedHashMap<String, Object>> yuanShiShuDataForLKJ =
                    Util.getYuanShiShuDataForLKJ(date, time, train_batch_no, region);
            pageBean.setRows(yuanShiShuDataForLKJ);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        Commons.getBuildPageBeanList(page, pageSize, pageBean);
        return pageBean;
    }

    /**
     * 查询lkj历史记录
     * 说明：查询的是分析出来的问题的原因
     *
     * @param page
     * @param pageSize
     * @param time           时间
     * @param train_batch_no 车次
     * @param driver_id      乘务员编号
     * @param region         记录行数
     * @return
     */
    @ResponseBody
    @RequestMapping("/findHostoryRecordWithLKJForList")
    public List<LinkedHashMap<String, Object>> findHostoryRecordWithLKJForList(String page, String pageSize
            , String date, String time, String train_batch_no, String region) {
        List<LinkedHashMap<String, Object>> list = null;
        try {
            list =Util.getYuanShiShuDataForLKJ(date, time, train_batch_no, region);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 2018-12-19
     * 保存分析结果数据
     * //页面对应图标====项点分析结果
     *
     * @param ywDriverOperationEvaluation
     * @return
     */
    @RequestMapping("/saveAnasysisResults")
    public @ResponseBody
    String saveAnasysisResults(String res) {
        String st = "";
        try {
            List<YwDriverOperationEvaluation> maps = Commons.jsonToList(res, YwDriverOperationEvaluation.class);
            //删除当前乘务员临时表中的数据,因为在一个方法同时处理数据容易导致数据库表锁死状态
            //checkOutService.deleteTempTblDataByDriverCode(maps);
            //保存数据
            //checkOutService.saveAnasysisResult(maps);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return st;
    }

    /**
     * 处理yw_checkout_operation_exception_tmp表数据到yw_checkout_operation_exception
     */
    @RequestMapping("/saveForTmpToOperationException")
    public void saveForTmpToOperationException(String driver_id) {
        try {
            checkOutService.saveForTmpToOperationException(driver_id);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
    /**
     *  @param request
     * @param response
     * @param jwcode 段代码
     */
    @RequestMapping("/saveComputerIp")
    public @ResponseBody String  saveComputerIp(HttpServletRequest request, HttpServletResponse response,String jwcode) {
        String ips = request.getHeader("X-FORWARDED-FOR");
        try {
            SysUser user = (SysUser)request.getSession().getAttribute("sissionuser");
            System.out.println("x-forwarded-for ips: " + ips);
            if (ips != null && ips.length() != 0 && !"unknown".equalsIgnoreCase(ips)) {
                // 多次反向代理后会有多个ips值，第一个ips才是真实ips
                if( ips.indexOf(",")!=-1 ){
                    ips = ips.split(",")[0];
                }
            }
            if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
                ips = request.getHeader("Proxy-Client-IP");
            }
            if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
                ips = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
                ips = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
                ips = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
                ips = request.getHeader("X-Real-IP");
            }
            if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
                ips = request.getRemoteAddr();
            }
            String reName = request.getRemoteHost();
            if(user!=null){
                checkOutService.saveComputerIp(user,jwcode,ips,reName);
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 检测网络是否正常
     * @param request
     * @param response
     * @param jwcode
     */
    @RequestMapping("/checkInternetIsOk")
    public @ResponseBody String checkInternetIsOk(HttpServletRequest request, HttpServletResponse response) {
        String str = "false";
        try {
            String ipPath = PlatFormPar.getPropertiesVal("IP_PATH");
            boolean connect = Commons.isConnect(ipPath);
            if(connect){
                str = "true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取lkj表头
     * @param request
     * @param response
     * @param jwcode
     */
    @RequestMapping("/getLKJColumsNames")
    public @ResponseBody List<Map<String,Object>> getLKJColumsNames(
            String date, String time, String train_batch_no, String driver_id, String region) {
        List<Map<String,Object>> list = null;
        try {

            list = checkOutService.getLKJColumsNames( date,  time,  train_batch_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}














