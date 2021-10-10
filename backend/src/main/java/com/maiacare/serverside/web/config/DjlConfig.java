package com.maiacare.serverside.web.config;

import ai.djl.modality.Classifications;
import ai.djl.modality.cv.util.BufferedImageUtils;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DjlConfig {
    @Bean
    public ZooModel retinaModel() throws Exception {
        Criteria<BufferedImage, Classifications> criteria =
                Criteria.builder()
                        .setTypes(BufferedImage.class, Classifications.class)
                        .optTranslator(new RetinaTranslator())
                        .build();        return ModelZoo.loadModel(criteria);
    }

    public static final class RetinaTranslator implements Translator<BufferedImage, Classifications>
    {
    private static final List<String> CLASSES = Arrays.asList("DR", "normal");
    @Override
    public NDList processInput(TranslatorContext ctx, BufferedImage input) {
        NDArray array =
                BufferedImageUtils.toNDArray(
                        ctx.getNDManager(), input, NDImageUtils.Flag.COLOR);
        array = NDImageUtils.resize(array, 224).div(255.0f);
        return new NDList(array);
    }    @Override
    public Classifications processOutput(TranslatorContext ctx, NDList list) {
        NDArray probabilities = list.singletonOrThrow();
        return new  Classifications(CLASSES, probabilities);
    }
    }

}
