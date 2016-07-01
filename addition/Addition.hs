module Addition where

import Test.Hspec
import Test.QuickCheck

dividedBy :: Integral a => a -> a -> (a, a)
dividedBy num denom = go num denom 0
  where go n d count
         | n < d     = (count, n)
         | otherwise = go (n - d) d (count + 1)

rMult :: (Eq a, Num a) => a -> a -> a
rMult 0 _ = 0
rMult _ 0 = 0
rMult a b = a + rMult a (b - 1)

genEither :: (Arbitrary a, Arbitrary b) => Gen (Either a b)
genEither = do
  a <- arbitrary
  b <- arbitrary
  elements [Left a, Right b]

main :: IO ()
main = hspec $ do
  describe "Addition" $ do
    it "1 + 1 is greater than 1" $ do
      (1 + 1) > 1 `shouldBe` True
    it "2 + 2 should equal 4" $ do
      (2 + 2) `shouldBe` 4
    it "15 divided by 3 is 5" $ do
      dividedBy 15 3 `shouldBe` (5, 0)
    it "22 divided by 5 is 4 remainder 2" $ do
      dividedBy 22 5 `shouldBe` (4, 2)
    it "x + 1 is always greater than x" $ do
      property $ \x -> x + 1 > (x :: Int)
  describe "rMult" $ do
    it "2 * 2 is equal to 4" $ do
      rMult 2 2 `shouldBe` 4
    it "0 * 1 is equal to 0" $ do
      rMult 0 1 `shouldBe` 0
    it "1 * 0 is equal to 0" $ do
      rMult 1 0 `shouldBe` 0
