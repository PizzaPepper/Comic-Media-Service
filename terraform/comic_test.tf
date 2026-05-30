terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region                      = "us-east-1"
  access_key                  = "test"
  secret_key                  = "test"
  s3_use_path_style           = true
  skip_credentials_validation = true
  skip_metadata_api_check     = true
  skip_requesting_account_id  = true
  endpoints {
    s3 = "http://localhost:4566"
  }
}


resource "aws_s3_bucket" "comics_bucket" {
  bucket = "comics"
}

# resource "aws_s3_bucket" "covers_bucket" {
#   bucket = "covers"
# }

resource "aws_s3_bucket_versioning" "versioning_comics" {
  bucket = aws_s3_bucket.comics_bucket.id

  versioning_configuration {
    status = "Enabled"
  }
}

# resource "aws_s3_bucket_versioning" "versioning_covers" {
#   bucket = aws_s3_bucket.covers_bucket.id

#   versioning_configuration {
#     status = "Enabled"
#   }
# }