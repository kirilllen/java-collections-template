package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        List listOfAllWords=getWords(text);
        int sumLengthOfWords=listOfAllWords
                .stream()
                .mapToInt(s->s.toString().length())
                .sum();

        return sumLengthOfWords;
    }

    @Override
    public int countNumberOfWords(String text) {
        List listOfAllWords=getWords(text);
        int numberOfWords=listOfAllWords
                .stream()
                .mapToInt(s->1)
                .sum();
        return numberOfWords;
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return 0;
    }

    @Override
    public List<String> getWords(String text) {
        //метод остался полностью аналогичным методу без стрима, так как не удалось переделать в стрим значения matcher.group()
        List<String> words=new ArrayList<>();

        String wordReg="\\w+\\b";
        Pattern pattern= Pattern.compile(wordReg);
        Matcher matcher=pattern.matcher(text);
        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        List<String> listOfAllWords=getWords(text);
        Set<String> uniqueWords=listOfAllWords.stream()
                .collect(Collectors.toSet());
        return uniqueWords;
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return emptyMap();
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return emptyList();
    }
}
