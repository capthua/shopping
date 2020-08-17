package com.shopping.samples.service;

import com.shopping.samples.model.Sample;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SampleService {

    int saveSample(Sample sample);

    List<Sample> list();



}
