package com.tenly.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tenly.common.projecttools.Commons;

import com.tenly.common.projecttools.PlatFormPar;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenly.project.bean.YwCheckutAnasysResult;
import com.tenly.project.service.ICheckOutBuffetQueryService;
import com.tenly.system.bean.PageBean;

@SuppressWarnings("all")
@Controller
@RequestMapping("/checkout/checkOutBuffetQuery")
public class CheckOutBuffetQueryController {
    @Autowired
    private ICheckOutBuffetQueryService checkOutBuffetQueryService;
    Logger logger = Logger.getLogger(this.getClass());

    /**
     * 页面跳转
     *
     * @param request
     * @param response
     * @param driver_code
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/buffetQuery")
    public String buffetQuery(HttpServletRequest request,
                              HttpServletResponse response, String driver_code) {
        request.setAttribute("driver_code", driver_code);
        return "/project/lzm/checkout-analysis/self-help-query/checkout-buffet-query-one";
    }

    /**
     * 历史查询，获取乘务员的出退勤时间，填充历史查询出退勤下拉选项
     * @param request
     * @param response
     * @param driver_code
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/getChuTuiQinDate")
    public @ResponseBody
    Map<String, Object> getChuTuiQinDate(HttpServletRequest request,
                                         HttpServletResponse response, String driver_code) {
        Map<String, Object> list = null;
        try {
            list = checkOutBuffetQueryService.getChuTuiQinDate(driver_code);
        } catch (Exception e) {

        }
        return list;
    }


    /**
     * 查询所有退勤详情
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/findAll")
    @ResponseBody
    public PageBean findAll(String page, String rows) {
        PageBean pb = new PageBean();
        checkOutBuffetQueryService.findAll(pb, page, rows);
        return pb;
    }

    /**
     * 查询所有项点名称
     *
     * @return
     */
    @RequestMapping("/findAllItemName")
    @ResponseBody
    public List<Map<String, Object>> findAllItemName() {
        PageBean pb = new PageBean();
        List<Map<String, Object>> list = checkOutBuffetQueryService.findAllItemName();
        return list;
    }

    /**
     * 作业执标分析表格
     */
    @RequestMapping("/itemsHabitsAna")
    @ResponseBody
    public PageBean itemsHabitsAna(String startDateToSingle,
                                   String endDateToSingle, String driver_code, String page, String pageSize) {
        PageBean pb = new PageBean();
        List<Map<String, Object>> list = checkOutBuffetQueryService.findItemsHabitsAna(
                "2018-05-01", "2018-11-16", driver_code);
        pb.setRows(list);

        Commons.getBuildPageBeanList(page, pageSize, pb);

        return pb;
    }

    /**
     * 项点条件查询
     * queryItemConditionOne
     * 这两个方法是前台避免同时发请求  请求数据,分发请求数据，视图展现延迟就不会太严重
     * "startDate=2018-04-20&end=2018-10-17&taskScene=0&taskItem=0&taskProblem=0"
     */
    @RequestMapping("/queryItemConditionOne")
    @ResponseBody
    public Map queryItemConditionOne(String startDate,
                                     String end, String taskScene,
                                     String taskItem, String taskItemPoint, String taskProblem, String driver_code) {
        Map map = null;
        try {
            map = checkOutBuffetQueryService.queryItemConditionOne(
                    startDate, end, taskScene, taskItem, taskItemPoint, taskProblem, driver_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 作业执标分析
     *
     * @param startDate     开始日期
     * @param endDate       结束日期
     * @param driver_code
     * @param taskScene
     * @param taskItem
     * @param taskItemPoint
     * @param taskProblem
     * @return
     */
    @RequestMapping("/queryItemConditionTwo")
    @ResponseBody
    public Map queryItemConditionTwo(String startDate, String endDate,
                                     String driver_code, String taskScene,
                                     String taskItem, String taskItemPoint, String taskProblem) {
        Map map = null;
        try {
            map = checkOutBuffetQueryService.QueryitemConditionTwo(
                    startDate, endDate,
                    driver_code, taskScene,
                    taskItem, taskItemPoint, taskProblem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据用户在操作项点分析中选择的项点级别去查询下级所有的项点 项目   问题
     */
    @RequestMapping("/queryItemByLevel")
    @ResponseBody
    public Map queryItemByLevel(String itemValue, String level) {
        Map map = null;
        try {
            map = checkOutBuffetQueryService.queryItemByLevel(itemValue, level);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //-----------------------------------------------自助退勤分析历史记录查询-----------------------------------

    /**
     * 退勤确认时间
     * 通过时间范围筛选乘务员的退勤确认时间
     *
     * @param driverCode 乘务员编号
     * @param startDate  开始日期
     * @param endDate    结束日期
     * @return List<Map               <               String               ,                               Object>>
     */
    @RequestMapping("/getCheckOutConfirmTime")
    @ResponseBody
    public List<Map<String, Object>> getCheckOutConfirmTime(String driverCode, String startDate, String endDate) {
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.getCheckOutConfirmTime(driverCode, startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 数据完整性分析
     */
    @RequestMapping("/getCurrentDriverStartAndEnd")
    @ResponseBody
    public List<Map<String, Object>> getCurrentDriverStartAndEnd(String driverCode, String sys_date) {
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.getCurrentDriverStartAndEnd(driverCode, sys_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 项点分析结果
     */
    @RequestMapping("/getCurrentDriverAnalyData")
    @ResponseBody
    public PageBean getCurrentDriverAnalyData(
            String page, String pageSize, String driver_id, String ondutytime, String offdutytime) {
        PageBean pb = new PageBean();
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.getCurrentDriverAnalyData(driver_id, ondutytime, offdutytime);
            if (map == null || map.size() < 1) {
                map = new ArrayList<Map<String, Object>>();
            }
            pb.setRows(map);
        } catch (Exception e) {
            e.printStackTrace();
            return pb;
        }
        Commons.getBuildPageBeanList(page, pageSize, pb);
        return pb;
    }

    /**
     * 项点分析结果
     */
    @RequestMapping("/getCurrentDriverAnalyDataToRes")
    @ResponseBody
    public List<Map<String, Object>> getCurrentDriverAnalyDataToRes(
            String driver_id, String ondutytime, String offdutytime) {
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.getCurrentDriverAnalyData(driver_id, ondutytime, offdutytime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * //YwCheckutAnasysResult
     * 项点分析结果
     */
    @RequestMapping("/getCurrentRowDetail")
    @ResponseBody
    public PageBean getCurrentRowDetail(YwCheckutAnasysResult yar, String page, String pageSize) {
        List<Map<String, Object>> map = null;
        PageBean pageBean = new PageBean();
        try {
            map = checkOutBuffetQueryService.getCurrentRowDetail(yar);
            pageBean.setRows(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Commons.getBuildPageBeanList(page, pageSize, pageBean);
        return pageBean;
    }

    /**
     * //YwCheckutAnasysResult
     * 项点分析结果
     */
    @RequestMapping("/getResultInfo")
    @ResponseBody
    public Map getResultInfo(String driver_id, String ondutytime, String offdutytime) {
        Map map = null;
        try {
            map = checkOutBuffetQueryService.getResultInfo(driver_id, ondutytime, offdutytime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 自助查询
     * 项点分析  作业违标分布点击查看作业问题 以及详情 到lkj数据
     */
    @RequestMapping("/getCommonDetail")
    @ResponseBody
    public List<Map<String, Object>> getCommonDetail(
            String startDate, String end, String driver_code, String field_name, String field_value, String problem_level,
            String taskScene, String taskItem, String taskItemPoint, String taskProblem) {
        List<Map<String, Object>> map = null;
        try {
             map = checkOutBuffetQueryService.getCommonDetail(
                    startDate, end, driver_code, field_name, field_value, problem_level,
                    taskScene, taskItem, taskItemPoint, taskProblem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 自助查询
     * 项点分析  作业违标分布点击查看作业问题 以及详情 到lkj数据
     */
    @RequestMapping("/getViolationDetail")
    @ResponseBody
    public List<Map<String, Object>> getViolationDetail(
            String startDate, String end, String driver_code, String item_code_name, String problem_level,
            String taskScene, String taskItem, String taskItemPoint, String taskProblem) {
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.getViolationDetail(
                    startDate, end, driver_code, item_code_name, problem_level,
                    taskScene, taskItem, taskItemPoint, taskProblem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 自助查询
     * 作业执标分析，操作评价及违标次数
     *
     * @param startDate     开始时间
     * @param endDate       结束时间
     * @param taskScene     作业场景
     * @param taskitem      作业项目
     * @param taskItemPoint 作业项点
     * @param taskProblem   作业问题
     * @return List<Map   <   String   ,       Object>>
     */
    @RequestMapping("/getTaskEvaluate")
    @ResponseBody
    public List<Map<String, Object>> getTaskEvaluate(String driver_code,
                                                     String startDate, String endDate,
                                                     String taskScene, String taskItem, String taskItemPoint, String taskProblem) {
        List<Map<String, Object>> list = null;
        try {
            list = checkOutBuffetQueryService.getTaskEvaluate(driver_code, startDate, endDate,
                    taskScene, taskItem, taskItemPoint, taskProblem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 自助查询
     * 项点分析  作业违标分布点击查看作业问题 以及详情 到lkj数据
     */
    @RequestMapping("/queryProblemViolationGridDetail")
    @ResponseBody
    public PageBean queryProblemViolationGridDetail(String id, String page, String pageSize,
                                                    String startDate, String endDate, String driver_code) {
        PageBean psb = new PageBean();
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.queryProblemViolationGridDetail(
                    startDate, endDate, driver_code, id);
            psb.setRows(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Commons.getBuildPageBeanList(page, pageSize, psb);
        return psb;
    }

    @RequestMapping("/queryProblemCommonDetail")
    @ResponseBody
    public PageBean queryProblemCommonGridDetail(String id, String page, String pageSize,
                                                 String startDate, String endDate, String driver_code, String field_name, String field_value) {
        PageBean psb = new PageBean();
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.queryProblemCommonGridDetail(
                    startDate, endDate, driver_code, id, field_name, field_value);
            psb.setRows(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Commons.getBuildPageBeanList(page, pageSize, psb);
        return psb;
    }

    /**
     * 作业评价
     * 气泡点击事件获取乘务员作业评价的内容
     */
    @RequestMapping("/getEvaluateContentByDriverCode")
    @ResponseBody
    public PageBean getEvaluateContentByDriverCode(
            String page, String pageSize, String driver_code, String evaldate) {
        PageBean pageBean = new PageBean();
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.getEvaluateContentByDriverCode(driver_code, evaldate);
            pageBean.setRows(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Commons.getBuildPageBeanList(page, pageSize, pageBean);
        return pageBean;
    }

    /**
     * 通过出勤日期获取退勤日期
     */
    @RequestMapping("/getTuiQinDate")
    @ResponseBody
    public List<Map<String, Object>> getTuiQinDate(String ondutytime, String driver_code) {
        List<Map<String, Object>> map = null;
        try {
            map = checkOutBuffetQueryService.getTuiQinDate(driver_code, ondutytime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 退勤历史查询
     *  o.page = params.pageNumber;
        o.pageSize = params.pageSize;
        o.ondutytime = ondutytime;
        o.offdutytime = offdutytime;
        o.driverCode = driver_code;
     */
    @RequestMapping("/findAllForChuTuiQinInfo")
    @ResponseBody
    public PageBean findAllForChuTuiQinInfo(
            String page,String pageSize,String ondutytime,String offdutytime,String driverCode) {
        PageBean pageBean=new PageBean();
        if(StringUtils.isBlank(page)||StringUtils.isEmpty(page)
                ||StringUtils.isBlank(pageSize)||StringUtils.isEmpty(pageSize)){
            return null;
        }
        pageBean.setCurrentPage(Integer.valueOf(page));
        pageBean.setPageSize(Integer.valueOf(pageSize));
        try {
            checkOutBuffetQueryService.findAllForChuTuiQinInfo( pageBean,ondutytime, offdutytime, driverCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageBean;
    }

}
