package tk.zhangh.struts1.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.struts1.config.ActionConfig;
import tk.zhangh.struts1.config.ModuleConfig;
import tk.zhangh.struts1.config.ModuleConfigImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 整个框架的入口
 *
 * 框架的初始化
 * 所有请求的入口
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class ActionServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(ActionServlet.class);

    RequestProcessor requestProcessor;
    ModuleConfig moduleConfig;

    /**
     * 框架初始化
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        logger.info(getClass().getName() + " init");
        initModuleConfig();
        initRequestProcess();
    }

    /**
     * 初始化ModuleConfig
     */
    private void initModuleConfig() {
        logger.info("init ModuleConfig");
        moduleConfig = new ModuleConfigImpl();
        List<ActionConfig> actionConfigs = createActionConfig();
        actionConfigs.forEach(o -> moduleConfig.addActionConfig(o));
    }

    /**
     * 创建ActionConfig实例列表
     * @return actionConfig列表
     */
    private List<ActionConfig> createActionConfig() {
        List<ActionConfig> actions = new ArrayList<>();
        ActionConfig actionConfig = new ActionMapping();
        actionConfig.setPath("/hello");
        actionConfig.setType("tk.zhangh.web.HelloAction");
        logger.info("add ActionConfig " + actionConfig);
        actions.add(actionConfig);
        return actions;
    }

    /**
     * 初始化RequestProcess
     */
    private void initRequestProcess() {
        logger.info("init RequestProcess");
        requestProcessor = new RequestProcessor();
        requestProcessor.init(moduleConfig);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("process GET request");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("process POST request");
        process(req, resp);
    }

    /**
     * 委托给RequestProcessor类进行处理
     */
    protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        requestProcessor.process(req, resp);
    }
}
