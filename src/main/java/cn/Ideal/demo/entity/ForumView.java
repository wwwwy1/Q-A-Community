package cn.Ideal.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class ForumView extends Forum{
	private List<String> tagList;
}
