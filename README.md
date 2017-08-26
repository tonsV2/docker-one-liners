# Init
http localhost:8081/api/init

# Find tags by rank
http localhost:8081/api/tagsByRank

# Search tags by partial tag
http localhost:8080/api/findTagsByName?name=ja

# Search oneliners by all tags
http localhost:8081/api/findByAllTags <<< '[db, sql]'

# Post new tag
http localhost:8081/api/tags <<< '{"name": "name", "description": "description"}'
