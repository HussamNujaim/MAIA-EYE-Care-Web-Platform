package com.maiacare.serverside.web.serviceImpl;

import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.util.BufferedImageUtils;
import ai.djl.repository.zoo.ZooModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class RetinaDiagnosisService {
    @Autowired
    private ZooModel retinaModel;
    public String diagnose(String imageUrl) {
        try (Predictor<BufferedImage, Classifications> predictor = retinaModel.newPredictor()) {
            Classifications result = predictor.predict(BufferedImageUtils.fromUrl(imageUrl));
            return "Diagnose: "
                    + result.best().getClassName()
                    + " , probability: "
                    + result.best().getProbability();
        } catch (Exception e) {
            throw new RuntimeException("Failed to diagnose", e);
        }
    }
}
