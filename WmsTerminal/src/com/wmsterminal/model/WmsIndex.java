package com.wmsterminal.model;

import java.io.Serializable;
import java.util.List;

public class WmsIndex extends BaseDTO{
	
	/*public WmsIndex(List<ContentEntity> ce){
		this.content_entity = ce;
	}*/
	private List<ContentEntity> content_entity;
	
	public List<ContentEntity> getContent_entity() {
		return content_entity;
	}

	public void setContent_entity(List<ContentEntity> content_entity) {
		this.content_entity = content_entity;
	}


	public static class ContentEntity implements Serializable{
		
		/*public ContentEntity(String c){
			this.content = c;
		}*/
		String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
	}

}
