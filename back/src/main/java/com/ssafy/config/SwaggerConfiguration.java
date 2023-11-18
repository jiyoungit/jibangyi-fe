package com.ssafy.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.google.common.base.Predicate;
//import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

//	Swagger-UI 2.x 확인
//	http://localhost:8080/{your-app-root}/swagger-ui.html
//	Swagger-UI 3.x 확인
//	http://localhost:8080/{your-app-root}/swagger-ui/index.html

	private final String version = "V1";
//	private String title = "SSAFY Board API " + version;
	
	private final ServerProperties serverProperties;

    public SwaggerConfiguration(ServerProperties serverProperties) {
		super();
		this.serverProperties = serverProperties;
	}

	@Bean
	public Docket allAPI() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes()).apiInfo(allApiInfo()).groupName("ALL-API").select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy")).paths(PathSelectors.ant("/**"))
				.build().useDefaultResponseMessages(false);
	}
	
	@Bean
	public Docket houseAPI() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes()).apiInfo(houseApiInfo()).groupName("HOUSE-API").select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.happyhouse.controller")).paths(PathSelectors.ant("/house/**"))
				.build().useDefaultResponseMessages(false);
	}

	@Bean
	public Docket usersAPI() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes()).apiInfo(userApiInfo()).groupName("USER-API").select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.member.controller")).paths(PathSelectors.ant("/user/**"))
				.build().useDefaultResponseMessages(false);
	}

	@Bean
	public Docket boardAPI() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes()).apiInfo(boardApiInfo()).groupName("BOARD-API").select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.board.controller")).paths(PathSelectors.ant("/board/**"))
				.build().useDefaultResponseMessages(false);
	}

	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
//      consumes.add("application/xml;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}

//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title(title).description(
//				"<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 Board API<br><img src=\"/assets/img/ssafy_logo.png\" width=\"150\">")
//				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com")).license("SSAFY License")
//				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp").version("1.0").build();
//	}
	
	private ApiInfo allApiInfo() {
        Integer port = serverProperties.getPort();
        return new ApiInfoBuilder()
            .title("JIBANGEE Swagger Open API Docs")
            .description(
                "<h3>SSAFY API Reference for Developers</h3>JIBANGEE를 위한 API")
            .version(version)
//            .license("SSAFY License")
//            .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
            .termsOfServiceUrl("http://edu.ssafy.com")
            .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }

    private ApiInfo userApiInfo() {
        return new ApiInfoBuilder()
            .title("사용자")
            .description("<h2>사용자 API</h2>")
            .version(version)
//            .license("SSAFY License")
//            .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
            .termsOfServiceUrl("http://edu.ssafy.com")
            .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    private ApiInfo boardApiInfo() {
        return new ApiInfoBuilder()
            .title("게시판")
            .description("<h2>게시판 API</h2>")
            .version(version)
//            .license("SSAFY License")
//            .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
            .termsOfServiceUrl("http://edu.ssafy.com")
            .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }
    
    private ApiInfo houseApiInfo() {
        return new ApiInfoBuilder()
            .title("아파트 매매 정보")
            .description("<h2>House API</h2>아파트 매매, 기본 정보를 얻을 수 있다.")
            .version(version)
//            .license("SSAFY License")
//            .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
            .termsOfServiceUrl("http://edu.ssafy.com")
            .contact(new Contact("SSAFY", "https://www.ssafy.com", "ssafy@ssafy.com"))
            .build();
    }

	// swagger ui 설정.
	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}

//	private List<ResponseMessage> getCustomizedResponseMessages() {
//		List<ResponseMessage> responseMessages = new ArrayList<>();
//		responseMessages.add(new ResponseMessageBuilder().code(200).message("성공").build());
////		responseMessages.add(new ResponseMessageBuilder().code(204).message("데이터 미존재").build());
//		responseMessages.add(new ResponseMessageBuilder().code(400).message("입력값 오류").build());
//		responseMessages.add(new ResponseMessageBuilder().code(401).message("로그인필요!!!").build());
//		responseMessages.add(new ResponseMessageBuilder().code(403).message("권한없음").build());
////		responseMessages.add(new ResponseMessageBuilder().code(412).message("처리중 오류").build());
//		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버에러").build());
//		return responseMessages;
//	}

}
