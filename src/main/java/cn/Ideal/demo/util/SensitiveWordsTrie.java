package cn.Ideal.demo.util;

import cn.Ideal.demo.entity.SensitiveWords;
import cn.Ideal.demo.service.ISensitiveWordsService;
import cn.hutool.dfa.WordTree;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public enum SensitiveWordsTrie {
	INSTANCE;
	private WordTree tree = new WordTree();
	public static final String SUBSTITUTE_WORDS ="\uD83E\uDD10";
	public void contentDefault(List<SensitiveWords> list){
		list.forEach(sensitiveWords -> tree.addWord(sensitiveWords.getContent()));
	}
	public void addContent(String content){
		tree.addWord(content);
	}
	public List<String> getSensitiveWords(String text){
		return tree.matchAll(text, -1, false, true);
	}
	public String replaceSensitiveWords(String text){
		List<String> sensitiveWords = getSensitiveWords(text);
		for (String s : sensitiveWords) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) sb.append(SensitiveWordsTrie.SUBSTITUTE_WORDS);
			text = text.replace(s,sb.toString());
		}
		return text;
	}
	public WordTree getTree() {
		return tree;
	}

	public void setTree(WordTree tree) {
		this.tree = tree;
	}
}
