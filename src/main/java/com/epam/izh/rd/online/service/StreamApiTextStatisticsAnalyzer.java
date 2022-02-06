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
        Set<String> uniqueWords=getUniqueWords(text);
        int numberOfUniqueWords=uniqueWords
                .stream()
                .mapToInt(s->1)
                .sum();
        return numberOfUniqueWords;
    }

    @Override
    public List<String> getWords(String text) {
        String wordReg="\\w+\\b";
        Pattern pattern= Pattern.compile(wordReg);
        Matcher matcher=pattern.matcher(text);
        int i=0;
        while (matcher.find()) {i++;}
        String[] matchGroups=new String[i];
        i=0;
        Matcher matcher2=pattern.matcher(text);
        while (matcher2.find()){
            matchGroups[i]=matcher2.group();
            i++;

        }
        List<String> words= Arrays.stream(matchGroups)
                .collect(Collectors.toList());
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
        List<String> listOfAllWords=getWords(text);
        Map<String, Integer> frequencyOfWords=listOfAllWords
                .stream()
                .collect(Collectors.toMap(s->s,s->1,Integer::sum));
        return frequencyOfWords;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> listOfAllWords=getWords(text);
        List<String> sortedList=new ArrayList<>();
        if (direction==Direction.ASC) {
            sortedList=listOfAllWords.stream()
                    .sorted((o1, o2) -> o1.length() - o2.length())
                    .collect(Collectors.toList());
        }
        if (direction==Direction.DESC) {
            sortedList=listOfAllWords.stream()
                    .sorted((o1, o2) -> o2.length() - o1.length())
                    .collect(Collectors.toList());
        }
        return sortedList;
    }
}
