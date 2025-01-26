package com.example.batch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskletResultService {
	private final List<Integer> results = new ArrayList<>();

	//結果をリストに追記
	public synchronized void addResult(int result) {
		results.add(result);
	}
	//結果を取得後、リストを空にする
	public synchronized List<Integer> getAndClearResults(){
		List<Integer> copiedResults = new ArrayList<>();
		results.clear();
		return copiedResults;
	}
	//リストが空になったか確認する
	public synchronized boolean hasResults() {
		return !results.isEmpty();
	}
}
