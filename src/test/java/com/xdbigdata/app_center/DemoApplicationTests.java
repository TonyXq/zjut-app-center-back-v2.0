package com.xdbigdata.app_center;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.xdbigdata.app_center.util.common.ClassUtils;
import com.xdbigdata.app_center.util.common.YMLUtil;
import com.xdbigdata.mybatis.Service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
//	@Autowired
//	private AppealService aService;

		String servicePackage = "com.xdbigdata.app_center.service";
		String domainPackage = "com.xdbigdata.app_center.domain";

	@Test
	public void contextLoads() throws Exception {
		Set<String> names = ClassUtils.getClassName(domainPackage, false);
		for (String name : names) {
			String domainName = name.replace(domainPackage+".", "");
			String interfaceName = "I"+domainName+"Service";
			TypeSpec interfaceTypeSpec = TypeSpec.interfaceBuilder(interfaceName).addModifiers(Modifier.PUBLIC).build();
			JavaFile interfaceFile = JavaFile.builder(servicePackage, interfaceTypeSpec).build();
			String targetDir = "src/main/java";
			File target = new File(targetDir);
			if (!target.exists()) {
				target.mkdirs();
			}
			interfaceFile.writeTo(target);

			//=================================
			String implName = domainName + "ServiceImpl";
			System.err.println("implName"+implName);
			System.err.println("interfaceName"+interfaceName);
			TypeSpec implClass = TypeSpec.classBuilder(implName)
					.addModifiers(Modifier.PUBLIC).addAnnotation(Service.class)
					.addSuperinterface(Class.forName(servicePackage+"."+interfaceName))
					.superclass(ParameterizedTypeName.get(BaseService.class, Class.forName(name)))
					.build();
			JavaFile implFile = JavaFile.builder(servicePackage + ".impl", implClass).build();
			if (!target.exists()) {
				target.mkdirs();
			}
			implFile.writeTo(target);
		}
	}

}
