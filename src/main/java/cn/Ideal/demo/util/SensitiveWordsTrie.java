package cn.Ideal.demo.util;

import cn.Ideal.demo.entity.SensitiveWords;
import cn.Ideal.demo.service.ISensitiveWordsService;
import cn.hutool.dfa.WordTree;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public enum SensitiveWordsTrie {
	INSTANCE;
	private WordTree tree = new WordTree();
	public void contentDefault(List<SensitiveWords> list){
		list.forEach(sensitiveWords -> tree.addWord(sensitiveWords.getContent()));
	}
	public void addContent(String content){
		tree.addWord(content);
	}
	public WordTree getTree() {
		return tree;
	}

	public void setTree(WordTree tree) {
		this.tree = tree;
	}
}
