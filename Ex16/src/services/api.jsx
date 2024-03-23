
import axios from 'axios';

const API_URL = 'http://localhost:8089/api';
const CATEGORY_ENDPOINT = '/categories';

export async function getCategoryList() {
  try {
    const response = await axios.get(`${API_URL}${CATEGORY_ENDPOINT}/`);
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch categories');
  }
}

export async function getCategoryById(categoryId) {
  try {
    const response = await axios.get(`${API_URL}${CATEGORY_ENDPOINT}/${categoryId}`);
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch category');
  }
}

export async function createNewCategory(newCategoryData, accessToken) {
  try {
    const response = await axios.post(
      `${API_URL}${CATEGORY_ENDPOINT}/`,
      {
        categoryCode: newCategoryData.categoryCode,
        name: newCategoryData.name,
        categoryDescription: newCategoryData.categoryDescription,
      },
      {
        headers: {
          Authorization: `Bearer ${accessToken}`,
          'Content-Type': 'application/json',
        },
      }
    );

    if (!response.data) {
      throw new Error('Failed to create category');
    }

    return response.data;
  } catch (error) {
    throw new Error('Failed to create category');
  }
}

// Trong services/api.js

export const updateCategory = async (categoryId, updatedCategoryData, accessToken) => {
  try {
    const response = await fetch(`${API_URL}${CATEGORY_ENDPOINT}/${categoryId}`, {
      method: 'PUT', // Sử dụng method PUT để cập nhật dữ liệu
      headers: {
        'Authorization': `Bearer ${accessToken}`,
        'Content-Type': 'application/json', // Xác định kiểu dữ liệu gửi đi
      },
      body: JSON.stringify(updatedCategoryData),
    });

    if (!response.ok) {
      throw new Error('Failed to update category');
    }

    return response.json();
  } catch (error) {
    throw new Error(error.message);
  }
};


