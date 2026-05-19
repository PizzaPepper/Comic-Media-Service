CREATE TABLE comic (
    manga_id UUID PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    status VARCHAR(100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE chapter (
    chapter_id UUID PRIMARY KEY,
    comic_id UUID,
    title VARCHAR(255),
    number NUMERIC(38,2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_chapter_comic
        FOREIGN KEY (comic_id)
        REFERENCES comic(manga_id)
        ON DELETE CASCADE
);

CREATE TABLE scan_group (
    scan_group_id UUID PRIMARY KEY,
    name VARCHAR(255),
    website VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE chapter_version (
    chapter_version_id UUID PRIMARY KEY,
    chapter_id UUID,
    scan_group_id UUID,
    CONSTRAINT fk_chapter_version_chapter
        FOREIGN KEY (chapter_id)
        REFERENCES chapter(chapter_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_chapter_version_scan_group
        FOREIGN KEY (scan_group_id)
        REFERENCES scan_group(scan_group_id)
        ON DELETE SET NULL
);

CREATE TABLE page (
    page_id UUID PRIMARY KEY,
    page_number INTEGER,
    image_path VARCHAR(500),
    chapter_version_id UUID,
    CONSTRAINT fk_page_chapter_version
        FOREIGN KEY (chapter_version_id)
        REFERENCES chapter_version(chapter_version_id)
        ON DELETE CASCADE
);