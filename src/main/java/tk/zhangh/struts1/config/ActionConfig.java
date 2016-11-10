package tk.zhangh.struts1.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Action类配置信息
 * Created by ZhangHao on 2016/11/9.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionConfig {
    /**
     * 请求路径
     */
    private String path = null;

    /**
     * 请求路径对应的Action全类名
     */
    private String type = null;
}