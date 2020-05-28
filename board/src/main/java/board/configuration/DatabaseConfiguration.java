package board.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
//application.properties를 사용할 수 있도록 설정 파일 위치 지정
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    //설정한 DB 정보를 사용하도록 지정, spring.datasource.hikari로 시작하는 설정을 이용해 히카리CP 설정 파일 만듦
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties(prefix="mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig(){
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean
    public DataSource dataSource() throws Exception{
        //히카리CP 설정 파일을 이용해 DB와 연결하는 데이터 소스 생성
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource.toString());      //정상 생성 확인
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //마이바티스 매퍼(SQL을 담고 있는 XML파일) 파일의 위치 설정
        sqlSessionFactoryBean.setMapperLocations
                (applicationContext.getResources("classpath:/mapper/**/sql-*"));
        sqlSessionFactoryBean.setConfiguration(mybatisConfig());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

