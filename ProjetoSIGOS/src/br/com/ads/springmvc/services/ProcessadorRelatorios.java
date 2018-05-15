package br.com.ads.springmvc.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
    "br.com.ads.springmvc.controller",
    "br.com.ads.springmvc.service"})
public class ProcessadorRelatorios {

	    @Bean
	    public JasperReportsViewResolver getJasperReportsViewResolver() {
	        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
	        resolver.setPrefix("/WEB-INF/resources/relatorios/");
	        resolver.setSuffix(".jasper");
	        resolver.setReportDataKey("datasource");
	        resolver.setViewNames("*_report");	        
	        resolver.setViewClass(JasperReportsMultiFormatView.class);
	        return resolver;
	    }
}
