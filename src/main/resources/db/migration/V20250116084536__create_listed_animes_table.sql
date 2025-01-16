CREATE TABLE IF NOT EXISTS listed_animes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    anime_id INTEGER NOT NULL,
    list_id UUID NOT NULL REFERENCES lists(id) ON DELETE CASCADE,
    "status" VARCHAR(255) NOT NULL,
    score INTEGER,
    favorite BOOLEAN DEFAULT FALSE,
    episodes_watched_count INTEGER DEFAULT 0,
    total_episodes INTEGER,
    started_at DATE,
    finished_at DATE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    
    UNIQUE (anime_id, user_id)
);