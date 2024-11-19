package infrastructure.wisesaying;

public class WiseSayingEntity {
	private Long id;
	private String wiseSaying;
	private String writer;

	public WiseSayingEntity() {
	}


	public WiseSayingEntity(String wiseSaying, String writer) {
		this.wiseSaying = wiseSaying;
		this.writer = writer;
	}

	public Long getId() {
		return id;
	}

	public String getWiseSaying() {
		return wiseSaying;
	}

	public void setWiseSaying(String wiseSaying) {
		this.wiseSaying = wiseSaying;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriter() {
		return writer;
	}

	public void setId(Long id) {
		this.id = id;
	}
}