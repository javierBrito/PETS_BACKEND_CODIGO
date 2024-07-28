package asedinfo.com.modelo.wordpress;
// default package

// Generated Oct 29, 2013 4:10:51 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persona generated by hbm2java
 */
@Entity
@Table(name = "wp_posts")
public class ProductoWP implements java.io.Serializable {
	private static final long serialVersionUID = -8622196233866959952L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Long Id;
	
	// Categoria
	@Column(name = "post_excerpt")
	private String postExcerpt;
	
	// Subcategoria
	@Column(name = "post_title")
	private String postTitle;
	
	@Column(name = "post_type")
	private String postType;

	public ProductoWP() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPostExcerpt() {
		return postExcerpt;
	}

	public void setPostExcerpt(String postExcerpt) {
		this.postExcerpt = postExcerpt;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
}
