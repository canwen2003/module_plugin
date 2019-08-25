package com.blued.android.module.plugin;

import com.android.build.gradle.BaseExtension;
import com.blued.android.module.interfaces.interfaces.Const;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * 插件所做工作：将注解生成器生成的初始化类汇总到ServiceLoaderInit，运行时直接调用ServiceLoaderInit
 */
public class ServiceLoaderPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        ServiceLoaderExtension extension = project.getExtensions()
                .create(Const.NAME, ServiceLoaderExtension.class);

        ServiceLoaderLogger.info("register transform");
        project.getExtensions().findByType(BaseExtension.class)
                .registerTransform(new ServiceLoaderTransform());

        project.afterEvaluate(p -> ServiceLoaderLogger.setConfig(extension));
    }
}
