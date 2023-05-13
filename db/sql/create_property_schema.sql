CREATE TABLE IF NOT EXISTS property (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  type varchar(20) NOT NULL DEFAULT 'RENTAL',
  owner_id bigint NOT NULL,
  address_id bigint NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY unique_owner (owner_id, address_id),
  KEY address_id_idx (address_id),
  KEY owner_id_idx (owner_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
