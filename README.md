# Plagiarism Detection Project Using Java and Cosine Similarity

## Introduction

This project is a plagiarism detection system that uses the text similarity algorithm (Cosine Similarity) to compare sentences in a given file with a set of reference files. The goal of this system is to analyze the similarity of an input text with reference files and generate a report on the percentage of similarity between them.

## Project Features

### 1. Input Data

- A text file to be analyzed.
- A set of reference files used for comparison.

### 2. Plagiarism Detection Process

- **Text Preprocessing**: Removing punctuation, normalizing words, and segmenting sentences.
- **Sentence Comparison**: Each sentence from the input file is compared with sentences from reference files.
- **Similarity Calculation**: The Cosine Similarity algorithm is used to calculate the similarity score for each sentence.
- **Final Report**: Similar sentences are identified, and the overall similarity percentage with each file is calculated and displayed.

### 3. Project Outputs

- Display of similar sentences along with similarity percentage and source file name.
- Calculation of the similarity percentage for each reference file separately.
- Calculation of the overall similarity percentage between the input file and all reference files.

## How the System Works

### 1. Selecting the Input File and Reference Files
The user selects a file for plagiarism detection and provides a set of reference files.

### 2. Text Analysis and Processing
- Sentences from the input file and reference files are extracted and preprocessed.
- Each sentence from the input file is compared with sentences in the reference files.

### 3. Calculating and Displaying Results
- The similarity score for each sentence is determined, and similar sentences are displayed with their similarity percentage.
- The overall similarity percentage of the input file with each reference file is calculated and displayed.
- Finally, the total similarity percentage of the input file across all reference files is provided.

## Algorithm Used (Cosine Similarity)

Cosine Similarity is a widely used method in Natural Language Processing (NLP) to measure the similarity between two texts. This algorithm calculates the angle between two text vectors in an n-dimensional space, where a value close to 1 indicates a higher similarity between sentences.

### Cosine Similarity Formula:

$$
\text{similarity}(A,B) = \frac{A \cdot B}{||A|| \times ||B||}
$$

Where:
- **A and B** are the word vectors of the two sentences being compared.
- **Numerator** is the dot product of the two vectors.
- **Denominator** is the product of the norms (magnitudes) of both vectors.

## How the Overall Similarity Percentage is Calculated

After computing sentence similarities, the similarity percentage between the input file and each reference file is determined as follows:

\[
\text{Similarity Percentage for Each File} = \frac{\text{Number of Similar Sentences}}{\text{Total Sentences in Input File}} \times 100
\]

Then, the overall similarity percentage across all reference files is computed using:

\[
\text{Overall Similarity Percentage} = \frac{\sum \text{Similarity Percentage for Each File}}{\text{Number of Similar Files}}
\]

## Example System Output

