package com.java.exercises.TabelaFipe.service;

import java.util.List;

public interface IConvertData {
  <T> T   obtainData(String json, Class<T> classes);
  <T> List<T>   obtainList(String json, Class<T> classes);
}
