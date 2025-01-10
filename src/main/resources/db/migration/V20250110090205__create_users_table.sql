CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    display_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    email_verified BOOLEAN DEFAULT FALSE,
    username VARCHAR(255) NOT NULL UNIQUE,
    bio TEXT,
    "provider" VARCHAR(255) NOT NULL DEFAULT 'LOCAL',
    avatar_url TEXT,
    password_hash VARCHAR(255) NOT NULL,
    watched_animes_count INT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);