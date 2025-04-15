package com.yj.springboot3actuator.observation;


import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author YangJian
 * @version 1.0.0
 * @title IndexObservation
 * @create 2025/4/14 21:36
 * @description <TODO description class purpose>
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class IndexObservation {

    private final ObservationRegistry observationRegistry;

    public void observe() {
        Observation.createNotStarted("indexObservation", this.observationRegistry)
                .lowCardinalityKeyValue("area", "cn")
                .highCardinalityKeyValue("userId", "100")
                .observe(() -> {
                    // 执行观测时的业务逻辑
                    log.info("开始执行业务逻辑...");
                });
    }

}
