module MonoidsOhMy where

import Data.Monoid
import Test.QuickCheck

data Optional a = Nada | Only a deriving (Eq, Show)

instance Monoid a => Monoid (Optional a) where
  mempty = Nada
  mappend Nada Nada = Nada
  mappend Nada b = b
  mappend a Nada = a
  mappend (Only a) (Only b) = Only (a <> b)

type Verb        = String
type Adjective   = String
type Adverb      = String
type Noun        = String
type Exclamation = String

---
---
---

madlibbin' :: Exclamation -> Adverb -> Noun -> Adjective -> String
madlibbin' e adv noun adj =
  e    <> "! he said "                  <>
  adv  <> " as he jumped into his car " <>
  noun <> " and he drove off with his " <>
  adj  <> " wife."

madlibbinBetter' :: Exclamation -> Adverb -> Noun -> Adjective -> String
madlibbinBetter' e adv noun adj =
  mconcat [e, "! he said ",
           adv, "as he jumped into his car ",
           noun, " and he drove off with his ",
           adj, " wife."]
---
---
---

monoidAssoc :: (Eq m, Monoid m) => m -> m -> m -> Bool
monoidAssoc a b c = (a <> (b <> c)) == ((a <> b) <> c)

monoidLeftIdentity :: (Eq m, Monoid m) => m -> Bool
monoidLeftIdentity a = (mempty <> a) == a

monoidRightIdentity :: (Eq m, Monoid m) => m -> Bool
monoidRightIdentity a = (a <> mempty) == a


newtype First' a =
  First' { getFirst' :: Optional a }
  deriving (Eq, Show)

instance Monoid (First' a) where
  mempty = First' Nada
  First' Nada `mappend` r = r
  l `mappend` _ = l

optionalGen :: Arbitrary a => Gen (Optional a)
optionalGen = do
  a <- arbitrary
  return (Only a)

instance Arbitrary a => Arbitrary (Optional a) where
  arbitrary = optionalGen

firstGen :: Arbitrary a => Gen (First' a)
firstGen = do
  a <- arbitrary
  return (First' a)

instance Arbitrary a => Arbitrary (First' a) where
  arbitrary = firstGen

firstMappend :: First' a -> First' a -> First' a
firstMappend = mappend

type FirstMappend =
  First' String -> First' String -> First' String -> Bool

type FstId =
  First' String -> Bool

qcTestFirst' :: IO ()
qcTestFirst' = do
  quickCheck (monoidAssoc :: FirstMappend)
  quickCheck (monoidLeftIdentity :: FstId)
  quickCheck (monoidRightIdentity :: FstId)
