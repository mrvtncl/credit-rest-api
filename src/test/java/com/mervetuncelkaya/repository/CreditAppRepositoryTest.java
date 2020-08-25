package com.mervetuncelkaya.repository;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mervetuncelkaya.FakeMongo;
import com.mervetuncelkaya.entitiy.CreditApp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Import(value = {FakeMongo.class})
@EnableMongoRepositories(basePackageClasses = {CreditAppRepository.class})
public class CreditAppRepositoryTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CreditAppRepository creditAppRepository;

    @Rule
    public MongoDbRule embeddedMongoDbRule = newMongoDbRule().defaultSpringMongoDb("mockDB");

    @Test
    @UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
    public void noCreditAppsTest() {
        List<CreditApp> creditApps = creditAppRepository.findAll();
        assertTrue("Kredi başvurusu bulunmuyor.", creditApps.isEmpty());
    }

    @Test
    @UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
    public void findByTckn() {
        creditAppRepository.save(new CreditApp("12345678910"));
        List<CreditApp> creditApps = creditAppRepository.findAll();
        Optional<CreditApp> ca = creditAppRepository.findById(creditApps.get(0).getId());
        assertTrue(ca.isPresent());
        assertEquals("12345678910", ca.get().getTckn());
    }
}
