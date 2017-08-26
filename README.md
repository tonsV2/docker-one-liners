# Init
http localhost:8081/api/init

# Find tags by rank
http localhost:8081/api/tagsByRank

# Search tags by partial tag
http localhost:8080/api/search/findTagsByName?name=ja

# Search oneliners by all tags
http localhost:8081/api/search/findByAllTags <<< '["db", "sql"]'
