package com.ssafy.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.google.common.base.Predicate;
//import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
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

	private String version = "V1";
	private String title = "SSAFY Board API " + version;
	
	@Bean
	public Docket houseAPI() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
					.apiInfo(apiInfo()).groupName("House" + version).select()
					.apis(RequestHandlerSelectors.basePackage("com.ssafy.happyhouse.controller"))
					.paths(regex("/house.*")).build()
					.useDefaultResponseMessages(false);
	}
	
	@Bean
	public Docket membersAPI() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
				.apiInfo(apiInfo()).groupName("Members" + version).select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.member.controller"))
				.paths(regex("/members.*")).build()
				.useDefaultResponseMessages(false);
	}
	
//	@Bean
//	public Docket userApi() {
//		return getDocket("users", Predicates.or(PathSelectors.regex("/user.*")));
////		return getDocket("회원", PathSelectors.ant("/user/*"));
//	}
	
	@Bean
	public Docket boardAPI() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
				.apiInfo(apiInfo()).groupName("Board" + version).select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.board.controller"))
				.paths(regex("/board.*")).build()
				.useDefaultResponseMessages(false);
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

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title)
				.description("<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 Board API<br><img src=\"/assets/img/ssafy_logo.png\" width=\"150\">") 
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
				.license("SSAFY License")
				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
				.version("1.0").build();
	}
//	
//	private String version = "V1";
//	private String title = "SSAFY Vuejs API " + version;
//
//	private ApiInfo apiInfo() {
//		String descript = "<img src=\"http://localhost/vue/static/assets/img/ssafy_logo.png\"><br>";
//		descript += "<h2>싸피 Vuejs 개발을 위한 API 문서입니다.</h2>";
//		descript += "회원정보, 게시판, 아파트 정보를 테스트 할 수 있습니다.";
//		return new ApiInfoBuilder().title(title).description(descript)
////				.termsOfServiceUrl("https://edu.ssafy.com")
//				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com")).license("SSAFY License")
//				.licenseUrl("ssafy@ssafy.com").version("1.0").build();
//	}
//
	// swagger ui 설정.
	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}
//	
//	@Bean
//	public Docket allApi() {
//		return getDocket("all", Predicates.or(PathSelectors.regex("/*.*")));
//	}
//
//	@Bean
//	public Docket userApi() {
//		return getDocket("users", Predicates.or(PathSelectors.regex("/user.*")));
////		return getDocket("회원", PathSelectors.ant("/user/*"));
//	}
//
//	@Bean
//	public Docket searchApi() {
//		return getDocket("boards", Predicates.or(PathSelectors.regex("/board.*")));
//	}
//
//	@Bean
//	public Docket commonApi() {
//		return getDocket("apts", Predicates.or(PathSelectors.regex("/map.*")));
//
//	}
//
//	public Docket getDocket(String groupName, Predicate<String> predicate) {
//		return new Docket(DocumentationType.SWAGGER_2).groupName(groupName).apiInfo(apiInfo()).select()
//				.apis(RequestHandlerSelectors.basePackage("com.ssafy.vue")).paths(predicate)
//				.apis(RequestHandlerSelectors.any()).build()
//				.globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
//				.globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
//				.globalResponseMessage(RequestMethod.DELETE, getCustomizedResponseMessages())
//				.globalResponseMessage(RequestMethod.PUT, getCustomizedResponseMessages())
//				.globalResponseMessage(RequestMethod.PATCH, getCustomizedResponseMessages());
//	}

//	
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
